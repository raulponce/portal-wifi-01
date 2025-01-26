FROM debian:bookworm

ENV DEBIAN_FRONTEND=noninteractive

RUN apt-get update --fix-missing
RUN apt-get -y install procps curl unzip dos2unix host telnet inetutils-ping net-tools vim socat tini
RUN apt-get -y install freeradius

ENTRYPOINT ["tini", "--", "/usr/sbin/freeradius",  "-X"]
#ENTRYPOINT ["tini", "--"]

CMD ["/bin/bash"]

