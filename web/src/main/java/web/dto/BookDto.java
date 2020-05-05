package web.dto;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class BookDto  extends BaseDto<String>{
    private String title;
    private String authorName;
    private String genre;
}
