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
package br.com.webbudget.domain.entities.registration;

import br.com.webbudget.domain.entities.PersistentEntity;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 20/05/2014
 */
@Entity
@Audited
@ToString(callSuper = true)
@Table(name = "wallet_balances")
@EqualsAndHashCode(callSuper = true)
@AuditTable(value = "audit_wallet_balances")
public class WalletBalance extends PersistentEntity {

    @Getter
    @Setter
    @Column(name = "actual_balance", nullable = false)
    private BigDecimal actualBalance;
    @Getter
    @Setter
    @Column(name = "old_balance", nullable = false)
    private BigDecimal oldBalance;
    @Getter
    @Setter
    @Column(name = "movemented_value", nullable = false)
    @NotNull(message = "{wallet-balance.movemented-value}")
    private BigDecimal movementedValue;
    @Getter
    @Setter
    @Column(name = "movement_code")
    private String movementCode;
    @Getter
    @Setter
    @Column(name = "reason", columnDefinition = "TEXT")
    private String reason;
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "balance_type", nullable = false)
    private BalanceType balanceType;
    
    @Getter
    @Setter
    @ManyToOne
    @NotNull(message = "{wallet-balance.null-wallet}")
    @JoinColumn(name = "id_wallet", nullable = false)
    private Wallet wallet;

    /**
     * 
     */
    public void processBalances() {
        
        // calculate the actual and the old balance
        this.oldBalance = this.wallet.getActualBalance();
        this.actualBalance = (this.wallet.getActualBalance()
                .add(this.movementedValue));
        
        // update the target wallet balance
        this.wallet.setActualBalance(this.actualBalance);
    }
}
