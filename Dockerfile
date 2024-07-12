FROM postgres:16.3-alpine3.20

COPY db/db.sql /docker-entrypoint-initdb.d/