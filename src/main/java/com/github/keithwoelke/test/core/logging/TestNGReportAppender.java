package com.github.keithwoelke.test.core.logging;

import ch.qos.logback.core.OutputStreamAppender;
import org.testng.Reporter;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;


@SuppressWarnings({"unused", "WeakerAccess"})
public final class TestNGReportAppender<E> extends OutputStreamAppender<E> {

    public TestNGReportAppender() {
        super();

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        setOutputStream(baos);
    }

    @Override
    protected final void subAppend(E event) {
        super.subAppend(event);

        Reporter.log(this.getOutputStream().toString());

        OutputStream outputStream = this.getOutputStream();

        if (outputStream instanceof ByteArrayOutputStream) {
            ((ByteArrayOutputStream) outputStream).reset();
        }
    }
}