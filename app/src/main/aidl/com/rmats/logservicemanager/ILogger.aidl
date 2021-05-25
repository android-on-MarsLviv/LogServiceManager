package com.rmats.logservicemanager;

import com.rmats.logservicemanager.LogLevel;

interface ILogger {
    oneway void log(in LogLevel logLevel, in String message);
}