#! /bin/sh

PRGDIR="`dirname "$0"`"
SDF_HOME="`cd "$PRGDIR"/.. && pwd`"
# fix sdf home when using cygwin
cygwin=false;
case "`uname`" in
    CYGWIN*) cygwin=true ;;
esac
if $cygwin; then
    if [ "$OS" == "Windows_NT" ]; then
        SDF_HOME="`cygpath --windows $SDF_HOME`"
    fi
fi
echo "Using SDF Home: $SDF_HOME"

JAVA_HOME="@JAVA_HOME@"
echo "Using JAVA_HOME: $JAVA_HOME"

LOG4J_CONF="file:$SDF_HOME/conf/log4j.xml"
echo "Log4j Config: $LOG4J_CONF"

JAAS_CONF="file:$SDF_HOME/conf/sdf_jaas.config"
echo "Jaas Config: $JAAS_CONF"

JVM_OPTS="
-Dapp.id=SDF \
-Dlog4j.configuration=$LOG4J_CONF \
-Djava.security.auth.login.config==$JAAS_CONF \
-Dcom.oksijen.conf.dir=$SDF_HOME/conf \
-Dsdf.home=$SDF_HOME \
-server \
@SWITCH_64BIT@ \
-Xms@MIN_HEAP_SIZE@ \
-Xmx@MAX_HEAP_SIZE@ \
-Xbatch \
-Xcomp \
-XX:CompileThreshold=10 \
-XX:NewSize=@NEW_SIZE@ \
-XX:MaxNewSize=@MAX_NEW_SIZE@ \
-XX:PermSize=@PERM_SIZE@ \
-XX:MaxPermSize=@MAX_PERM_SIZE@ \
-XX:MaxTenuringThreshold=0 \
-XX:SurvivorRatio=1024 \
-XX:TargetSurvivorRatio=80 \
-XX:+UseConcMarkSweepGC \
-XX:+UseParNewGC \
-XX:+CMSParallelRemarkEnabled \
-XX:ParallelGCThreads=8 \
-XX:CMSInitiatingOccupancyFraction=80 \
-XX:+UseCMSInitiatingOccupancyOnly \
-Xloggc:gc.log \
-XX:PrintCMSStatistics=2 \
-XX:+PrintGCDetails \
-XX:+PrintGCTimeStamps \
-XX:+PrintTenuringDistribution \
-XX:+PrintHeapAtGC \
-XX:+PrintGCApplicationConcurrentTime \
-XX:+PrintGCApplicationStoppedTime"
echo "Using JVM OPTS: $JVM_OPTS"

EQUINOX_JAR="$SDF_HOME/bundles/org.eclipse.osgi_3.4.0.v20080605-1900.jar" 
EQUINOX_OPTS="-configuration $SDF_HOME/equinox"

cd $SDF_HOME

JAVA_EXEC_DIR="$JAVA_HOME"/bin
if [ -d "$JAVA_HOME"/bin/sparcv9 ]; then
	JAVA_EXEC_DIR=$JAVA_EXEC_DIR/sparcv9
fi
"$JAVA_EXEC_DIR"/java $JVM_OPTS -jar $EQUINOX_JAR $EQUINOX_OPTS > out.txt 2>&1