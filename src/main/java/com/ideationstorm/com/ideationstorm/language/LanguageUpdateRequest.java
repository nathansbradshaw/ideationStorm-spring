package com.ideationstorm.com.ideationstorm.language;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LanguageUpdateRequest {
    private long id;
    private String name;
}
