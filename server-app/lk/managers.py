from django.db import models


def get_select_related(sr, model):
    _select_related = model.objects._select_related if type(model.objects) == MainManager else None
    _select_related = _select_related or []
    return [sr] + ['%s__%s' % (sr, _sr) for _sr in _select_related]


class MainManager(models.Manager):
    def __init__(self, *args, **kwargs):
        _select_related = kwargs.pop('select_related', None)
        self._prefetch_related = kwargs.pop('prefetch_related', None)

        _select_related = [get_select_related(_sr, _select_related[_sr]) for _sr in _select_related]
        _select_related = sum(_select_related, [])
        # self._select_related = None
        self._select_related = _select_related

        super(MainManager, self).__init__(*args, **kwargs)

    def get_queryset(self, *args, **kwargs):
        qs = super(MainManager, self).get_queryset(*args, **kwargs)

        if self._select_related:
            qs = qs.select_related(*self._select_related)
        if self._prefetch_related:
            qs = qs.prefetch_related(*self._prefetch_related)

        return qs