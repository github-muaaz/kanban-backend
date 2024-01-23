package com.example.kanbanbackend.utils;

import com.example.kanbanbackend.entity.CommonStatus;
import com.example.kanbanbackend.entity.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface Constants {
    String[] colors = {"#67E2AE", "#8471F2", "#49C4E5", "#00b533", "#ffffff", "#dfe3e8", "#c4cdd5", "#002930", "#00b533",
            "#e2f5e9", "#45b36b", "#007b55", "#1890ff", "#0c53b7", "#30c062", "#229a16", "#ffc107", "#b78103", "#ff4842",
            "#b72136", "#002930",};
    List<CommonStatus> commonColumns = List.of(
            CommonStatus.builder()
                    .id(UUID.randomUUID())
                    .color(Constants.colors[0])
                    .orderNum(100.)
                    .name("todo")
                    .build(),
            CommonStatus.builder()
                    .id(UUID.randomUUID())
                    .color(Constants.colors[1])
                    .orderNum(200.)
                    .name("doing")
                    .build(),
            CommonStatus.builder()
                    .id(UUID.randomUUID())
                    .color(Constants.colors[2])
                    .orderNum(300.)
                    .name("done")
                    .build()
    );
}
