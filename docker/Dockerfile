# Set the base image to Ubuntu
FROM ubuntu

# Set the maintainer
MAINTAINER Christoph Burmeister

# Add the application
ADD /@application.jar@ /

# Update the repository sources list
RUN apt-get update

# Install wget
RUN apt-get -fy install wget

# Create jdk directory and enter that directory
RUN mkdir /opt/jdk
RUN cd /opt/jdk

# Download Java package from Oracle
RUN wget --header "Cookie: oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/8u5-b13/jdk-8u5-linux-x64.tar.gz

# Untar the package
RUN tar -zxf jdk-8u5-linux-x64.tar.gz -C /opt/jdk

# Set Oracle's Java as default
RUN update-alternatives --install /usr/bin/java java /opt/jdk/jdk1.8.0_05/bin/java 100

# Show java version
RUN java -version

# Expose the application port
EXPOSE @server.port@

# Set default container command
ENTRYPOINT ["java", "-jar", "/@application.jar@"]