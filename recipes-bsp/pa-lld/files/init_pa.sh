#*
#* Copyright (C) 2015 Texas Instruments Incorporated - http://www.ti.com/
#*
#*
#*  Redistribution and use in source and binary forms, with or without
#*  modification, are permitted provided that the following conditions
#*  are met:
#*
#*    Redistributions of source code must retain the above copyright
#*    notice, this list of conditions and the following disclaimer.
#*
#*    Redistributions in binary form must reproduce the above copyright
#*    notice, this list of conditions and the following disclaimer in the
#*    documentation and/or other materials provided with the
#*    distribution.
#*
#*    Neither the name of Texas Instruments Incorporated nor the names of
#*    its contributors may be used to endorse or promote products derived
#*    from this software without specific prior written permission.
#*

#! /bin/sh
compatible=$(cat /proc/device-tree/compatible)

cd /usr/lib
case "$compatible" in
	*k2hk*)
		device=k2hk
		ln -sf libpa.so.1.0.0 libpa_device.so.1
	;;
	*k2l*)
		device=k2l
		ln -sf libpa2.so.1.0.0 libpa_device.so.1
	;;
	*k2e*)
	device=k2e
		ln -sf libpa2.so.1.0.0 libpa_device.so.1
	;;
	*)
		device=unknown
	;;
esac

if [ $device != unknown ]; then
	ln -sf libpa_device.so.1 libpa_device.so
	echo PA library link established for device : $device
fi
