# add a config to point piglit at the default install location for deqp tests
# if they are selected

FILESEXTRAPATHS:prepend := "${THISDIR}/piglit:"

PACKAGECONFIG[deqp-gles] = ",,,opengl-es-cts"
PACKAGECONFIG[deqp-vk] = ",,,vulkan-cts"

do_install:append() {
    if ${@bb.utils.contains('PACKAGECONFIG', 'deqp-gles', 'true', 'false', d)}; then
        printf "%s\n" \
            "[deqp-egl]" \
            "bin=/usr/lib/opengl-es-cts/deqp-egl" \
            "[deqp-gles2]" \
            "bin=/usr/lib/opengl-es-cts/deqp-gles2" \
            "[deqp-gles3]" \
            "bin=/usr/lib/opengl-es-cts/deqp-gles3" \
            "[deqp-gles31]" \
            "bin=/usr/lib/opengl-es-cts/deqp-gles31" \
            >> ${D}/${libdir}/piglit/piglit.conf
    fi
    if ${@bb.utils.contains('PACKAGECONFIG', 'deqp-vk', 'true', 'false', d)}; then
        printf "%s\n" \
            "[deqp-vk]" \
            "bin=/usr/lib/vulkan-cts/deqp-vk" \
            "[deqp-vksc]" \
            "bin=/usr/lib/vulkan-cts/deqp-vksc" \
            >> ${D}/${libdir}/piglit/piglit.conf
    fi
}
