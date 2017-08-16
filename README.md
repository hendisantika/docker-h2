# Spring Boot Docker H@


So our desired image id is the latest userservice: 717df7ce7063. With this information we can create our docker run command:

?
1
run -d -v /home/christoph/logs:/var/log -p 127.0.0.1:18080:8080 717df7ce7063
What das this mean?

run -> ok that is simply the start command
-d -> detached mode, the container is created and started and then detached from the console
-v -> volume mapping. The directory /var/log inside the container is mapped to /home/christoph/logs on the host. This is important for the logs that are created by the application in /var/log inside the container (/path/on/host:/path/in/container)
-p -> port mapping. This means, that port 8080 inside the container is mapped to 18080 outside the container and additionally this is only reachable from 127.0.0.1. You might change this to 0.0.0.0 when running this in production. Otherwise the service in the container can only be reached from localhost.