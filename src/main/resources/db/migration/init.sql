/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- subway 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `subway` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `subway`;

-- 테이블 subway.covid 구조 내보내기
CREATE TABLE IF NOT EXISTS `covid` (
  `id` bigint(20) DEFAULT NULL,
  `hospital_id` bigint(20) DEFAULT NULL,
  `member_id` bigint(20) DEFAULT NULL,
  `programmer_id` bigint(20) DEFAULT NULL,
  `hospital_type_code` varchar(255) DEFAULT NULL,
  `city_code_hospital` int(11) DEFAULT NULL,
  `hospital_region_code` varchar(255) DEFAULT NULL,
  `available_extra_rooms_in_hospital` int(11) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `ward_type` varchar(255) DEFAULT NULL,
  `ward_facility_code` varchar(255) DEFAULT NULL,
  `bed_grade` double DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `city_code_patient` varchar(255) DEFAULT NULL,
  `type_of_admission` varchar(255) DEFAULT NULL,
  `severity_of_Illness` varchar(255) DEFAULT NULL,
  `visitors_with_patient` int(11) DEFAULT NULL,
  `admission_deposit` double DEFAULT NULL,
  `stay` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 subway.hospital 구조 내보내기
CREATE TABLE IF NOT EXISTS `hospital` (
  `id` int(11) DEFAULT NULL,
  `name` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 subway.member 구조 내보내기
CREATE TABLE IF NOT EXISTS `member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=98856 DEFAULT CHARSET=utf8mb4;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 subway.programmer 구조 내보내기
CREATE TABLE IF NOT EXISTS `programmer` (
  `id` bigint(20) DEFAULT NULL,
  `member_id` bigint(20) DEFAULT NULL,
  `hobby` varchar(64) DEFAULT NULL,
  `open_source` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `student` varchar(64) DEFAULT NULL,
  `employment` varchar(255) DEFAULT NULL,
  `formal_education` varchar(255) DEFAULT NULL,
  `undergrad_major` varchar(255) DEFAULT NULL,
  `company_size` varchar(255) DEFAULT NULL,
  `dev_type` text,
  `years_coding` varchar(255) DEFAULT NULL,
  `years_coding_prof` varchar(255) DEFAULT NULL,
  `job_satisfaction` varchar(255) DEFAULT NULL,
  `career_satisfaction` varchar(255) DEFAULT NULL,
  `hope_five_years` varchar(255) DEFAULT NULL,
  `job_search_status` varchar(255) DEFAULT NULL,
  `last_new_job` varchar(255) DEFAULT NULL,
  `assess_job1` varchar(255) DEFAULT NULL,
  `assess_job2` varchar(255) DEFAULT NULL,
  `assess_job3` varchar(255) DEFAULT NULL,
  `assess_job4` varchar(255) DEFAULT NULL,
  `assess_job5` varchar(255) DEFAULT NULL,
  `assess_job6` varchar(255) DEFAULT NULL,
  `assess_job7` varchar(255) DEFAULT NULL,
  `assess_job8` varchar(255) DEFAULT NULL,
  `assess_job9` varchar(255) DEFAULT NULL,
  `assess_job10` varchar(255) DEFAULT NULL,
  `assess_benefits1` varchar(255) DEFAULT NULL,
  `assess_benefits2` varchar(255) DEFAULT NULL,
  `assess_benefits3` varchar(255) DEFAULT NULL,
  `assess_benefits4` varchar(255) DEFAULT NULL,
  `assess_benefits5` varchar(255) DEFAULT NULL,
  `assess_benefits6` varchar(255) DEFAULT NULL,
  `assess_benefits7` varchar(255) DEFAULT NULL,
  `assess_benefits8` varchar(255) DEFAULT NULL,
  `assess_benefits9` varchar(255) DEFAULT NULL,
  `assess_benefits10` varchar(255) DEFAULT NULL,
  `assess_benefits11` varchar(255) DEFAULT NULL,
  `job_contact_priorities1` varchar(255) DEFAULT NULL,
  `job_contact_priorities2` varchar(255) DEFAULT NULL,
  `job_contact_priorities3` varchar(255) DEFAULT NULL,
  `job_contact_priorities4` varchar(255) DEFAULT NULL,
  `job_contact_priorities5` varchar(255) DEFAULT NULL,
  `job_email_priorities1` varchar(255) DEFAULT NULL,
  `job_email_priorities2` varchar(255) DEFAULT NULL,
  `job_email_priorities3` varchar(255) DEFAULT NULL,
  `job_email_priorities4` varchar(255) DEFAULT NULL,
  `job_email_priorities5` varchar(255) DEFAULT NULL,
  `job_email_priorities6` varchar(255) DEFAULT NULL,
  `job_email_priorities7` varchar(255) DEFAULT NULL,
  `update_CV` varchar(255) DEFAULT NULL,
  `currency` varchar(255) DEFAULT NULL,
  `salary` varchar(255) DEFAULT NULL,
  `salary_type` varchar(255) DEFAULT NULL,
  `converted_salary` varchar(255) DEFAULT NULL,
  `currency_symbol` varchar(255) DEFAULT NULL,
  `communication_tools` text,
  `time_fully_productive` varchar(255) DEFAULT NULL,
  `education_types` text,
  `self_taught_types` text,
  `time_after_bootcamp` varchar(255) DEFAULT NULL,
  `hackathon_reasons` text,
  `agree_disagree1` varchar(255) DEFAULT NULL,
  `agree_disagree2` varchar(255) DEFAULT NULL,
  `agree_disagree3` varchar(255) DEFAULT NULL,
  `language_worked_with` text,
  `language_desire_next_year` text,
  `database_worked_with` text,
  `database_desire_next_year` text,
  `platform_worked_with` text,
  `platform_desire_next_year` text,
  `framework_workedWith` text,
  `framework_desireNextYear` text,
  `IDE` text,
  `operating_system` varchar(255) DEFAULT NULL,
  `number_monitors` varchar(255) DEFAULT NULL,
  `methodology` varchar(255) DEFAULT NULL,
  `version_control` varchar(255) DEFAULT NULL,
  `checkIn_code` text,
  `ad_blocker` text,
  `ad_blocker_disable` text,
  `ad_blocker_reasons` text,
  `ads_agree_disagree1` text,
  `ads_agree_disagree2` text,
  `ads_agree_disagree3` text,
  `ads_actions` text,
  `ads_priorities1` text,
  `ads_priorities2` text,
  `ads_priorities3` text,
  `ads_priorities4` text,
  `ads_priorities5` text,
  `ads_priorities6` text,
  `ads_priorities7` text,
  `ai_dangerous` text,
  `ai_interesting` text,
  `ai_responsible` text,
  `ai_future` text,
  `ethics_choice` text,
  `ethics_report` text,
  `ethics_responsible` text,
  `ethicalImplications` text,
  `stack_overflow_recommend` text,
  `stack_overflow_visit` text,
  `stack_overflow_has_account` text,
  `stack_overflow_participate` text,
  `stack_overflow_jobs` text,
  `stack_overflow_dev_story` text,
  `stack_overflow_jobs_recommend` text,
  `stack_overflow_consider_member` text,
  `hypothetical_tools1` text,
  `hypothetical_tools2` text,
  `hypothetical_tools3` text,
  `hypothetical_tools4` text,
  `hypothetical_tools5` text,
  `wake_time` text,
  `hours_computer` text,
  `hours_outside` text,
  `skip_meals` text,
  `ergonomic_devices` text,
  `exercise` text,
  `gender` text,
  `sexual_orientation` text,
  `education_parents` text,
  `race_ethnicity` text,
  `dependents` text,
  `military_US` text,
  `survey_too_long` text,
  `survey_easy` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 내보낼 데이터가 선택되어 있지 않습니다.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;