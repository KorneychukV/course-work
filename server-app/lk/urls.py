from django.urls import path

from . import views

urlpatterns = [
    path('', views.test),
    path('login', views.lk_login),
    path('login_test', views.login_test),
    path('admin_test', views.admin_test),
    path('get_sections', views.get_sections),
    path('get_courses', views.get_courses),
    path('get_public_programs', views.get_public_programs),
    path('get_programs', views.get_study_programs),
    path('start_test', views.start_test),
    path('get_question', views.get_question),
    path('put_answer', views.put_answer),
    path('get_result', views.get_result),
    path('get_user_info', views.get_user_info),
    path('get_literature', views.get_program_literature),
    path('get_users', views.get_users),
    path('statistics', views.get_students_statistics),
    path('put_order', views.put_order),
    path('logout', views.lk_logout),
    path('get_question_admin', views.get_question_admin),
    path('get_webinars', views.get_webinars),
    path('get_certificates', views.get_certificates),
]