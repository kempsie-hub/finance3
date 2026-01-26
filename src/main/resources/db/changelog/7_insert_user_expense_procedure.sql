

DROP PROCEDURE IF EXISTS insert_user_expense;
DELIMITER //

CREATE PROCEDURE insert_user_expense(
    IN p_expense_type BIGINT,
    IN p_user_id BIGINT,
    IN p_name VARCHAR(255),
    IN p_description VARCHAR(255),
    IN p_expense_type_subscription_id BIGINT,
    IN p_amount NUMERIC(20,2),
    IN p_expense_date TIMESTAMP,
    IN p_created_by VARCHAR(50),
    OUT p_status varchar(50),
    OUT p_message VARCHAR(255)
)
BEGIN
    DECLARE expense_type_exists INT DEFAULT 0;
    DECLARE user_exists INT DEFAULT 0;
    DECLARE subscription_exists INT DEFAULT 0;

    -- Label for exit
    proc_exit: BEGIN

        -- Validate name
        IF p_name IS NULL OR p_name = '' THEN
            SET p_status = 'F0001';
            SET p_message = 'Name cannot be null or empty';
            LEAVE proc_exit;
        END IF;

        -- Validate amount
        IF p_amount <= 0 THEN
            SET p_status = 'F0002';
            SET p_message = 'Amount must be greater than 0';
            LEAVE proc_exit;
        END IF;

        -- Validate expense_type
        SELECT COUNT(*) INTO expense_type_exists
        FROM expense_type
        WHERE id = p_expense_type;

        IF expense_type_exists = 0 THEN
            SET p_status = 'F0003';
            SET p_message = 'Expense type does not exist';
            LEAVE proc_exit;
        END IF;

        -- Validate user
        SELECT COUNT(*) INTO user_exists
        FROM expense_users
        WHERE id = p_user_id;

        IF user_exists = 0 THEN
            SET p_status = 'F0004';
            SET p_message = 'User does not exist';
            LEAVE proc_exit;
        END IF;

        -- Validate subscription if provided
        IF p_expense_type_subscription_id IS NOT NULL THEN
            SELECT COUNT(*) INTO subscription_exists
            FROM expense_type_subscription
            WHERE id = p_expense_type_subscription_id;

            IF subscription_exists = 0 THEN
                SET p_status = 'F0005';
                SET p_message = 'Subscription does not exist';
                LEAVE proc_exit;
            END IF;
        END IF;

        -- Validate user
        SELECT COUNT(*) INTO user_exists
        FROM expense_users
        WHERE id = p_created_by;

        IF user_exists = 0 THEN
            SET p_status = 'F0006';
            SET p_message = 'Created By User does not exist';
            LEAVE proc_exit;
        END IF;



        -- Assign p_name to p_description if p_description is null
        IF p_description IS NULL THEN
            SET p_description = p_name;
        END IF;

        -- Insert the expense
        INSERT INTO user_expense (
            expense_type_id,
            user_id,
            name,
            description,
            expense_type_subscription_id,
            amount,
            expense_date,
            created_by,
            created_date
        ) VALUES (
            p_expense_type,
            p_user_id,
            p_name,
            p_description,
            p_expense_type_subscription_id,
            p_amount,
            p_expense_date,
            p_created_by,
            NOW()
        );

        SET p_status = 'SUCCESS';
        SET p_message = 'Expense inserted successfully';

    END proc_exit;
END //

