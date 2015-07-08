#!/bin/bash
export http_proxy=http://192.168.93.188:81/
export https_proxy=$http_proxy
curl "http://ca.tilab.com/builds/SSEFrontend/last/ssefrontend-install-package.deb" > "/tmp/ssefrontend-install-package.deb"
dpkg -r ssefrontendapp
dpkg -i /tmp/ssefrontend-install-package.deb
echo "ssefrontend installed"
