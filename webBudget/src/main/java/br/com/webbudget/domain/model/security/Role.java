/*
 * Copyright (C) 2015 Arthur Gregorio, AG.Software
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.com.webbudget.domain.model.security;

import br.com.webbudget.domain.model.entity.PersistentEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 2.0.0
 * @since 2.0.0, 26/05/2015
 */
@Entity
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "roles", schema = "security")
public class Role extends PersistentEntity {

    @Getter
    @Setter
    @Column(name = "permission", nullable = false, length = 90)
    private String permission;
    
    @Getter
    @Setter
    private Group group;

    /**
     * 
     */
    public Role() {
    }

    /**
     * 
     * @param permission 
     */
    public Role(String permission) {
        this.permission = permission;
    }
}
