INSERT INTO project (project_name, project_identifier, description, start_date, end_date) VALUES('clouds', 'cloud1', 'proyecto de prueba', '2019-05-02', '2021-05-02');

INSERT INTO backlog (project_identifier, project_id) VALUES('cloud1', 1);

INSERT INTO projecttask (name, summary, acceptance_criteria, status, priority, hours, start_date, end_date, project_identifier, backlog_id) VALUES('desarrollo', 'desarrollo de cloud', 'producto funcional', 'in progress', 3, 4, NOW(), NOW(), 'cloud1', 1);

INSERT INTO projecttask (name, summary, acceptance_criteria, status, priority, hours, start_date, end_date, project_identifier, backlog_id) VALUES('pruebas', 'pruebas de cloud', 'pruebas en ambiente de qa', 'completed', 2, 2, NOW(), NOW(), 'cloud2', 1);