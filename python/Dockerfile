FROM python:3
COPY kafka.py /kafkarun.py
RUN pip install kafka-python
CMD [ "python3", "-u", "/kafkarun.py" ]