#!/bin/sh
SERVICE_NAME=ssefrontend
FRONTEND_PATH=/opt/ssefrontend/
JAR_NAME=SSEFrontendApp.jar
PID_PATH_NAME=/tmp/ssefrontend-pid
case $1 in
    start)
        echo "Starting $SERVICE_NAME ..."
        if [ ! -f $PID_PATH_NAME ]; then
            nohup java -Dfrontend.path=$FRONTEND_PATH -jar $FRONTEND_PATH$JAR_NAME /tmp 2>> /dev/null >> /dev/null &
                        echo $! > $PID_PATH_NAME
            echo "$SERVICE_NAME started ..."
        else
            echo "$SERVICE_NAME is already running ..."
        fi
    ;;
    stop)
        if [ -f $PID_PATH_NAME ]; then
            PID=$(cat $PID_PATH_NAME);
            echo "$SERVICE_NAME stopping ..."
            kill $PID;
            echo "$SERVICE_NAME stopped ..."
            rm $PID_PATH_NAME
        else
            echo "$SERVICE_NAME is not running ..."
        fi
    ;;
    restart)
        if [ -f $PID_PATH_NAME ]; then
            PID=$(cat $PID_PATH_NAME);
            echo "$SERVICE_NAME stopping ...";
            kill $PID;
            echo "$SERVICE_NAME stopped ...";
            rm $PID_PATH_NAME
            echo "$SERVICE_NAME starting ..."
            nohup java -Dfrontend.path=$FRONTEND_PATH -jar $FRONTEND_PATH$JAR_NAME /tmp 2>> /dev/null >> /dev/null &
                        echo $! > $PID_PATH_NAME
            echo "$SERVICE_NAME started ..."
        else
            echo "$SERVICE_NAME is not running ..."
        fi
    ;;
	status)
		if [ -f $PID_PATH_NAME ]; then
            PID=$(cat $PID_PATH_NAME);
			echo "$SERVICE_NAME is running (pid $PID)."
		else
			echo "$SERVICE_NAME is NOT running."
		fi
	;;
	
	
esac