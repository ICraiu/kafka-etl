sudo docker container rm -f postgres
sudo docker run --name postgres -p 5432:5432 -d -e POSTGRES_PASSWORD=password -e POSTGRES_DB=MBD_TEST postgres
