INSANE_SKIP:${PN} += "ldflags"
INSANE_SKIP:${PN}-openmp += "ldflags"

PR:append = ".ti1"

BRANCH = "sdk"
SRCREV = "96156d407d9a4e5fac4513f3d3f60a414b3355cd"

do_compile:prepend() {
    #Explicitly clear some variables to insure no unexpected optimizations are being passed in.
    unset CFLAGS
    unset LDFLAGS
}
