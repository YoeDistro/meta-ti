# This is required since cairo expects the opengl distro feature to refer to
# the desktop version of OpenGL. Imagination drivers only provide OpenGLES.

PACKAGECONFIG:remove = "opengl"
PACKAGECONFIG:append = " glesv2"
