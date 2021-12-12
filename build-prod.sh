#!/bin/bash
echo "---BUILDING ANGULAR docker---"
cd ./from-to-education
docker build -f Dockerfile-prod -t edu/nginx .
echo "---BUILDING DJANGO docker---"
cd ../server-app
docker build . -t edu/server-app
