package org.litesoft.utils;

public interface UuidNonDashCharOffsets {
    int[] HEX_DIGIT_OFFSETS = {
            // 60fba26b-11b9-4fa8-96b7-674080889fff
            // 01234567-101234567-201234567-3012345
            0, 1, 2, 3, 4, 5, 6, 7, //. . . . . . . . . . . . . 60fba26b
            9, 10, 11, 12, // . . . . . . . . . . . . . . . . . 11b9
            14, 15, 16, 17, //. . . . . . . . . . . . . . . . . 4fa8
            19, 20, 21, 22, //. . . . . . . . . . . . . . . . . 96b7
            24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35 // . 674080889fff
    };
}
