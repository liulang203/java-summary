package io.ddnet.vuesimple.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Vinson.Ding on 2018/5/25.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    private String id;
    private String icon;
    private boolean activity;
    private String name;
    private String href;
    private List<Menu> subMenus;
}
