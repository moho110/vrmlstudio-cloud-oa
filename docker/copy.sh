#!/bin/sh

# 复制项目的文件到对应docker路径，便于一键生成镜像。
usage() {
	echo "Usage: sh copy.sh"
	exit 1
}


# copy sql
echo "begin copy sql "
cp ../sql/ry_20210908.sql ./mysql/db
cp ../sql/ry_config_20220114.sql ./mysql/db

# copy html
echo "begin copy html "
cp -r ../vrmlstudio-ui/dist/** ./nginx/html/dist


# copy jar
echo "begin copy vrmlstudio-gateway "
cp ../vrmlstudio-gateway/target/vrmlstudio-gateway.jar ./VrmlStudio/gateway/jar

echo "begin copy vrmlstudio-auth "
cp ../vrmlstudio-auth/target/vrmlstudio-auth.jar ./VrmlStudio/auth/jar

echo "begin copy vrmlstudio-visual "
cp ../vrmlstudio-visual/vrmlstudio-monitor/target/vrmlstudio-visual-monitor.jar  ./VrmlStudio/visual/monitor/jar

echo "begin copy vrmlstudio-modules-system "
cp ../vrmlstudio-modules/vrmlstudio-system/target/vrmlstudio-modules-system.jar ./VrmlStudio/modules/system/jar

echo "begin copy vrmlstudio-modules-file "
cp ../vrmlstudio-modules/vrmlstudio-file/target/vrmlstudio-modules-file.jar ./VrmlStudio/modules/file/jar

echo "begin copy vrmlstudio-modules-job "
cp ../vrmlstudio-modules/vrmlstudio-job/target/vrmlstudio-modules-job.jar ./VrmlStudio/modules/job/jar

echo "begin copy vrmlstudio-modules-gen "
cp ../vrmlstudio-modules/vrmlstudio-gen/target/vrmlstudio-modules-gen.jar ./VrmlStudio/modules/gen/jar

