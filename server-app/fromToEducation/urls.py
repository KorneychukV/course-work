from django.contrib import admin
from django.urls import include, path
from rest_framework_jwt.views import obtain_jwt_token, refresh_jwt_token

urlpatterns = [
    path('lk/', include('lk.urls')),
    path('admin/', admin.site.urls),
    # path(r'^_nested_admin/', include('nested_admin.urls')),
]