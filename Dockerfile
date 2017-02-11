FROM phusion/baseimage:0.9.19

# Use baseimage-docker's init system.
CMD ["/sbin/my_init"]

RUN locale-gen en_US.UTF-8  
ENV LANG en_US.UTF-8  
ENV LANGUAGE en_US:en  
ENV LC_ALL en_US.UTF-8  

RUN apt-get update
RUN apt-get install -y default-jre
RUN apt-get install -y sqlite3

RUN mkdir /usr/databaslab3

COPY ./bin 	/usr/databaslab3
COPY ./src	/usr/databaslab3
COPY ./Main.jar /usr/databaslab3
COPY ./lab2.sql /usr/databaslab3
COPY ./sqlite-jdbc.jar /usr/databaslab3

EXPOSE 1645

WORKDIR /usr/databaslab3

RUN sqlite3 lab2.db < lab2.sql


CMD ["java", "-cp", "Main.jar", "src.MovieBooking"]

# Clean up APT when done.
RUN apt-get clean && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*
