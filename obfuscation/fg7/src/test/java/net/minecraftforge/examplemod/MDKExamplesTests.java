/*
 * Copyright (c) Forge Development LLC
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package net.minecraftforge.examplemod;

import mezz.jei.forge.input.ForgeJeiKeyMapping;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;

import java.io.File;
import java.io.IOException;
import java.util.zip.ZipFile;

// NOTE: This is a test class specifically for our MDKExamples repo
// This is NOT meant for end modders to consume, this is for our automated tests
public class MDKExamplesTests {
    protected static File getFileFromResource(String resource) {
        var cl = MDKExamplesTests.class.getClassLoader();
        var url = cl.getResource(resource);
        if (url == null)
            throw new IllegalStateException("Could not find " + resource + " in classloader " + cl);

        var str = url.toString();
        int len = resource.length();
        if ("jar".equalsIgnoreCase(url.getProtocol())) {
            str = url.getFile();
            len += 2;
        }
        if (!str.startsWith("file:/"))
            Assertions.fail("File resource expected, found: " + str);
        str = str.substring(6, str.length() - len);
        var ret = new File(str);
        Assertions.assertTrue(ret.exists(), "Found resource " + url + ", but file " + str + " does not exist");
        return ret;
    }

    @Test
    public void jeiDeobfuscated() throws IOException {
        var clsName = ForgeJeiKeyMapping.class.getName().replace('.', '/') + ".class";
        var jei = getFileFromResource(clsName);
        try (var zip = new ZipFile(jei)) {
            var entry = zip.getEntry(clsName);
            Assertions.assertNotNull(entry, "Could not find " + clsName + " in " + jei);
            var bytes = zip.getInputStream(entry).readAllBytes();
            var cls = new ClassNode();
            new ClassReader(bytes).accept(cls, 0);
            var mtd = cls.methods.stream().filter(m -> m.name.equals("isUnbound")).findFirst().orElse(null);
            Assertions.assertNotNull(mtd, "isUnbound method not found");
            for (var ins : mtd.instructions) {
                if (ins instanceof MethodInsnNode misn) {
                    Assertions.assertEquals("isUnbound", misn.name, "Method Invocation was not renamed");
                    return; // Success
                }
            }

            Assertions.fail("Did not find expected method invocation");
        }
    }
}
