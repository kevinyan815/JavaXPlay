package com.learncollection.learnstream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GetUniqueList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(
                Arrays.asList(
                        "pingan-accident-150-2021-jf",
                        "pingan-accident-150-2021-jf-old",
                        "zhonghui-illness-30w-2021-jf",
                        "zhonghui-health-2021-plus",
                        "huatal-accident-shaoer-2022-jf",
                        "huatal-accident-student-2022-jf",
                        "huatal-accident-child-2022-jf",
                        "huatai-accident-old-2022-jf-20w",
                        "zhongan-health-huiyibao-jf",
                        "taipingyang-health-2020A-jf",
                        "zhonghui-health-cancer-ganbu30",
                        "zhonghui-health-cancer-ganbu20",
                        "zhonghui-health-cancer-ganbu10",
                        "zhonghui-health-2023-plus-tpa",
                        "zhonghui-health-cancer-ganbu10",
                        "zhonghui-health-cancer-ganbu20",
                        "zhonghui-health-cancer-ganbu30",
                        "pingan-accident-150-2021-jf",
                        "pingan-accident-150-2021-jf-old",
                        "taipingyang-health-2020A-jf",
                        "huatal-accident-child-2022-jf",
                        "huatal-accident-shaoer-2022-jf",
                        "huatal-accident-student-2022-jf",
                        "huatai-accident-old-2022-jf-20w",
                        "zhonghui-illness-30w-2023-jf-tpa",
                        "zhonghui-health-2023-plus-tpa",
                        "zhongan-health-huiyibao-jf-dy-tpa-2023-b"
                )
        );
        list = list.stream().distinct().collect(Collectors.toList());
        System.out.println(list);
    }
}
