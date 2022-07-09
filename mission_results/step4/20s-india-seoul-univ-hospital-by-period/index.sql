ALTER TABLE `subway`.`hospital`
    CHANGE COLUMN `id` `id` INT (11) NOT NULL,
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE INDEX `id_UNIQUE` (`id` ASC);
;

ALTER TABLE `subway`.`member`
    CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL,
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE INDEX `id_UNIQUE` (`id` ASC);
;

ALTER TABLE `subway`.`programmer`
    CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL,
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE INDEX `id_UNIQUE` (`id` ASC);
;

CREATE INDEX `idx_covid_member_id` ON `subway`.`covid` (hospital_id) COMMENT '' ALGORITHM DEFAULT LOCK DEFAULT
CREATE INDEX `idx_covid_member_id` ON `subway`.`covid` (member_id) COMMENT '' ALGORITHM DEFAULT LOCK DEFAULT
CREATE INDEX `idx_covid_member_id` ON `subway`.`covid` (programmer_id) COMMENT '' ALGORITHM DEFAULT LOCK DEFAULT

CREATE UNIQUE INDEX `idx_hospital_name` ON `subway`.`hospital` (name) COMMENT '' ALGORITHM DEFAULT LOCK DEFAULT
