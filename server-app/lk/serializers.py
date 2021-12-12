from rest_framework import serializers

from lk.models import TestTry, StudyProgramLiterature, EducationRequest


class TestTrySerializer(serializers.ModelSerializer):
    class Meta:
        model = TestTry
        fields = ['id', 'start_date', 'end_date', 'is_test', 'is_final', 'is_complete', 'is_successful']


class OrderSerializer(serializers.ModelSerializer):
  class Meta():
    model = EducationRequest
    fields = ('fio', 'organization', 'phone', 'email', 'additional_info_file')