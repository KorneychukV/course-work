from functools import wraps

from django.core.exceptions import PermissionDenied
from django.db import models
from django.forms import model_to_dict

from lk.models import StudyProgram, UserInfo


def rest_login_required(view):
    @wraps(view)
    def wrapper(request, *args, **kwargs):
        if not request.user.is_authenticated:
            raise PermissionDenied
        return view(request, *args, **kwargs)
    return wrapper


def admin_required(view):
    @wraps(view)
    def wrapper(request, *args, **kwargs):
        if not request.user.is_superuser:
            raise PermissionDenied
        return view(request, *args, **kwargs)
    return wrapper


def to_dict(instance, ignore_to_dict=False, exclude=None):
    if instance is None:
        return None

    if hasattr(instance, 'to_dict') and not ignore_to_dict:
        return instance.to_dict()

    dict = model_to_dict(instance, exclude=exclude)
    for f in instance._meta.fields:
        if type(f) == models.ForeignKey and not (exclude and '{}_id'.format(f.name) in exclude):
            dict['{}_id'.format(f.name)] = getattr(instance, '{}_id'.format(f.name))

        if exclude and f.name in exclude:
            continue

        obj = getattr(instance, f.name)
        if type(f) == models.ForeignKey:
            dict[f.name] = to_dict(obj)
        if type(f) == models.DateTimeField:
            dict[f.name] = int(obj.timestamp()*1000)
    return dict

def to_list(object_list, exclude=None):
    return [to_dict(instance, exclude=exclude) for instance in object_list]

def path_to_program(program_id):
    query = '''
    WITH RECURSIVE ctename AS (
        SELECT id, parent_id, name, 0 as level
        FROM lk_studyprogram
        WHERE id = %s
        UNION ALL
        SELECT lk_studyprogram.id, lk_studyprogram.parent_id, lk_studyprogram.name, ctename.level + 1
        FROM lk_studyprogram
            JOIN ctename ON lk_studyprogram.id = ctename.parent_id
    ) SELECT id, name FROM ctename ORDER BY level DESC;
    '''
    sps = StudyProgram.objects.raw(query, [program_id])
    res = '. '.join([sp.name for sp in sps])
    return res


def get_users_id_list(lastname, firstname, thirdname):
    users_id = UserInfo.objects.filter(lastname__icontains=lastname, firstname__icontains=firstname,
                                        thirdname__icontains=thirdname).values('user_id')
    users_id = list(users_id)
    result = tuple([id['user_id'] for id in users_id]) if len(users_id)>0 else []
    return result