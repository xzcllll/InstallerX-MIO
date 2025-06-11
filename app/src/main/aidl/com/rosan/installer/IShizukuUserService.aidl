package com.rosan.installer;

import com.rosan.installer.IPrivilegedService;

interface IShizukuUserService {
    void destroy();

    IPrivilegedService getPrivilegedService();
}
