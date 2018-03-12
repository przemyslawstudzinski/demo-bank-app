package org.banana.bank.mapper;

import org.banana.bank.domain.TransactionType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class TransactionTypeMapper {

    /**
     * Get TransactionType object from display name.
     *
     * @param displayName display name of TransactionType
     *
     * @return TransactionType object
     */
    public TransactionType toTransactionType(String displayName) {
        if (displayName == null || displayName.isEmpty()) {
            return null;
        }

        return TransactionType.getByDisplayName(displayName);
    }

    /**
     * Get display name from TransactionType.
     *
     * @param type type of transaction.
     *
     * @return display name of TransactionType
     */
    public String fromTransactionType(TransactionType type) {
        if (type == null) {
            return null;
        }

        return type.toString();
    }
}
