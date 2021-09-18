SRC_URI_append = " file://Dockerfile"
do_compile(){
    rm -rf ${B}/work
    mkdir -p ${B}/work
}
