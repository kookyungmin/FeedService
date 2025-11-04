package net.happykoo.feed.admin.ui.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetTableListResponse<T> {
    private List<T> tableData;
    private int totalCount;
}
