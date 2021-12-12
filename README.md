# Для первого запуска
sh build-prod.sh
docker-compose up
# найти ID контейнера edu/server-app
docker exec -it <ID контейнера> python3 manage.py migrate
docker exec -it <ID контейнера> python3 manage.py createsuperuser
docker exec -it <ID контейнера> python3 manage.py makemigrations lk
docker exec -it <ID контейнера> python3 manage.py migrate
# Для последующих
docker-compose up
