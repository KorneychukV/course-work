FROM python:3.8
ENV PYTHONUNBUFFERED 1
WORKDIR /server-app
COPY requirements.txt /server-app/
RUN pip install --upgrade pip && pip install -r requirements.txt
COPY . /server-app/
RUN mkdir -p /server-app/lk/migrations/ && \
    touch /server-app/lk/migrations/__init__.py
CMD python3 manage.py runserver 0.0.0.0:8000
