package com.ideationstorm.com.ideationstorm.category;

import com.ideationstorm.com.ideationstorm.project.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryUpdateRequest {
    private long id;
    private String name;
}
