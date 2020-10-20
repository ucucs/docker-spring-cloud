#!/bin/bash
# Clean Docker Shell-Script.
docker rmi -f $(docker images | grep "<none>" | awk "{print \$3}")
docker rm $(docker ps --all -q -f status=exited)