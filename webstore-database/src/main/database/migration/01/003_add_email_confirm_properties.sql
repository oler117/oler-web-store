--liquibase formatted sql

--changeset oles:add_email_confirm_properties

--  +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
-- ++++++++++++++++++++++++++++++++++++++ TEST DATA ++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
INSERT INTO test_db_data.property (p_key, p_value)
VALUES ('email.confirmation.senderemail', 'datinferno522@gmail.com');

INSERT INTO test_db_data.property (p_key, p_value)
VALUES ('email.confirmation.senderpsw', 'MtIBw5QE');

INSERT INTO test_db_data.property (p_key, p_value)
VALUES ('email.confirmation.subject', '[Web Store Name] - Sign Up Confirmation');

INSERT INTO test_db_data.property (p_key, p_value)
VALUES ('email.confirmation.htmlbodytemplate', '<h2 style="text-align: center">Greetings from [Web Store]!</h2>
<div style="background: #99c5da;
    color: #000000;
    text-align: center;
    font-size: 16pt;
    padding: 20px 5px">

    There is only one step to finish your registration. Visit the link:<br/><br/>
    {0}
</div>');

-- INSERT INTO test_db_data.property (p_key, p_value)
-- VALUES ('email.confirmation.senderemail', '');
--
-- INSERT INTO test_db_data.property (p_key, p_value)
-- VALUES ('email.confirmation.senderpsw', '');
--
-- INSERT INTO test_db_data.property (p_key, p_value)
-- VALUES ('email.confirmation.subject', '');
--
-- INSERT INTO test_db_data.property (p_key, p_value)
-- VALUES ('email.confirmation.htmlbodytemplate', '');

--rollback DELETE FROM test_db_data.property WHERE p_key LIKE 'email.confirmation.%';