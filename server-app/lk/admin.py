import json

from django import forms
from django.contrib import admin
from .models import *
from django.contrib.auth.models import User
from django.contrib.auth.admin import UserAdmin as BaseUserAdmin
import nested_admin


admin.site.register(StudySection)
admin.site.register(Course)


class AnswerInline(nested_admin.NestedStackedInline):
    model = Answer
    extra = 0
class QuestionInline(nested_admin.NestedStackedInline):
    model = Question
    inlines = [AnswerInline, ]
    extra = 0
class LiteratureInline(nested_admin.NestedStackedInline):
    model = StudyProgramLiterature
    extra = 0
class StudyProgramForm(forms.ModelForm):
    class Meta:
        model = StudyProgram
        exclude = []
class StudyProgramAdmin(nested_admin.NestedModelAdmin):
    form = StudyProgramForm
    inlines = [LiteratureInline, QuestionInline]

    # def add_view(self, request, form_url='', extra_context=None):
    #     if request.method == 'POST':
    #         parent_id = request.POST['parent']
    #         if len(parent_id) > 0:
    #             parent_id = int(parent_id)
    #             sp = StudyProgram.objects.get(id = parent_id)
    #             sp.is_test = False
    #             sp.save()
    #     return super(StudyProgramAdmin, self).add_view(request, form_url, extra_context)

    # def delete_view(self, request, object_id, extra_context=None):
    #     if request.method == 'POST':
    #         sp = StudyProgram.objects.get(id = object_id)
    #         if sp.parent is not None:
    #             parent = StudyProgram.objects.get(id=sp.parent_id)
    #             childs_count = StudyProgram.objects.filter(parent_id=parent.id).count()
    #             if childs_count == 1:
    #                 parent.is_test = True
    #                 parent.save()
    #     return super(StudyProgramAdmin, self).delete_view(request, object_id, extra_context)

    # def change_view(self, request, object_id, form_url='', extra_context=None):
    #     if request.method == 'POST':
    #         parent_id = request.POST['parent']
    #         if len(parent_id) > 0:
    #             parent = StudyProgram.objects.get(id = parent_id)
    #             parent.is_test = False
    #             parent.save()
    #         else:
    #             sp = StudyProgram.objects.get(id=object_id)
    #             if sp.parent is not None:
    #                 parent = StudyProgram.objects.get(id=sp.parent_id)
    #                 childs_count = StudyProgram.objects.filter(parent_id=parent.id).count()
    #                 if childs_count == 1:
    #                     parent.is_test = True
    #                     parent.save()
    #
    #     return super(StudyProgramAdmin, self).change_view(request, object_id, form_url, extra_context)


admin.site.register(StudyProgram, StudyProgramAdmin)


# admin.site.register(Contract)
class ContractInline(admin.StackedInline):
    model = Contract
    extra = 0
class StudyGroupsForm(forms.ModelForm):
    # Выводим только те программы обучения, которые не устарели
    def __init__(self, *args, **kwargs):
        super(StudyGroupsForm, self).__init__(*args, **kwargs)
        self.fields['program'].queryset = StudyProgram.objects.filter(is_deprecated=False)

    class Meta:
        model = StudyGroups
        exclude = []


class StudyGroupsAdmin(admin.ModelAdmin):
    form = StudyGroupsForm
    inlines = [ContractInline,]

    def add_view(self, request, form_url='', extra_context=None):
        if request.method == 'POST':
            program_id = request.POST['program']
            program = StudyProgram.objects.get(id=program_id)
            alias = program.study_group_alias
            groups_with_alias = StudyGroups.objects.filter(name__startswith=alias).count()
            request.POST._mutable = True
            request.POST['name'] = '{}-{}'.format(alias, groups_with_alias + 1)
            request.POST._mutable = False
        return super(StudyGroupsAdmin, self).add_view(request, form_url, extra_context)

    # def change_view(self, request, object_id, form_url='', extra_context=None):
    #     if request.method == 'POST':
    #         program_id = request.POST['program']
    #         group = StudyGroups.objects.get(id=object_id)
    #         if group.program.id != program_id:
    #             program = StudyProgram.objects.get(id = program_id)
    #             alias = program.study_group_alias
    #             groups_with_alias = StudyGroups.objects.filter(name__startswith='{}-'.format(alias)).count()
    #             request.POST._mutable = True
    #             request.POST['name'] = '{}-{}'.format(alias, groups_with_alias + 1)
    #             request.POST._mutable = False
    #     return super(StudyGroupsAdmin, self).change_view(request, object_id, form_url, extra_context)


admin.site.register(StudyGroups, StudyGroupsAdmin)


# admin.site.register(EducationRequest)


class UserInfoInline(admin.StackedInline):
    model = UserInfo
    can_delete = False
    # verbose_name_plural = 'employee'


# Define a new User admin
class UserAdmin(BaseUserAdmin):
    inlines = (UserInfoInline,)


admin.site.unregister(User)
admin.site.register(User, UserAdmin)

admin.site.register(Webinar)
admin.site.register(Certificate)