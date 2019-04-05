package com.zuehlke.testing.rules.exercises;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

class LogWriterTest {

    private final LogWriter logWriter = new LogWriter();

    @Test
    void log(@TempDir Path tmpDir) throws IOException {
        Path filePath = tmpDir.resolve("logfile.txt");
        File logFile = new File(filePath.toUri());

        logWriter.log(logFile, "test");

        assertThat(logFile.canRead(), equalTo(true));
        String fileContent = new String(Files.readAllBytes(logFile.toPath()));
        assertThat(fileContent, containsString("test"));
    }
}