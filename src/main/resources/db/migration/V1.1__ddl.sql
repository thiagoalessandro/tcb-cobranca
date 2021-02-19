/*********************************************************************************
                                Log Transacional
**********************************************************************************/
create table tbl_log_transacional
(
    id           bigserial   not null
        constraint pk_log_transacional
            primary key,
    chave        bigint,
    dominio      varchar(15),
    req_method   varchar(10),
    req_query    varchar(10),
    req_path     varchar(200),
    req_id       varchar(36),
    resp_status  smallint,
    resp_errors  varchar(255),
    resp_time_ms int,
    resp         text,
    exception    text,
    ip           varchar(20),
    trace_id     varchar(20) not null,
    dh_ini       timestamp   not null,
    dh_fim       timestamp   not null,
    cd_usu       varchar(25) not null
);

create index idx_log_transacional_req_id on tbl_log_transacional (req_id);

/*********************************************************************************
                            Log Evento Mensageria
**********************************************************************************/

create table tbl_log_evento_mensageria
(
    id_log        bigserial not null
        constraint tbl_log_evento_mensageria_pkey
            primary key,
    dh_evento     timestamp,
    dh_processado timestamp,
    topico        varchar(255),
    uuid          varchar(255)
);

create index idx_uuid
    on tbl_log_evento_mensageria (uuid);

/*********************************************************************************
                                  Certificado
**********************************************************************************/

create table tbl_certificado
(
    id_cer         bigserial    not null
        constraint pk_tbl_certificado
            primary key,
    certificado    oid          not null,
    nm_arq_origem  varchar(150) not null,
    tp_certificado varchar(20)  not null,
    cd_transacao   bigint       not null,
    senha          varchar(255),
    id_log         bigint
        constraint fk_certificado_log_evento_mensageria
            references tbl_log_evento_mensageria,
    cd_usu_atu     varchar(25),
    dh_atu         timestamp    not null,
    id_sit         varchar(1)   not null
);

/*********************************************************************************
                           Conta Integradora CFOP
**********************************************************************************/

create table tbl_conta_integradora_cfop
(
    id_cnta_intg_cfop bigserial   not null
        constraint pk_conta_integradora_cfop
            primary key,
    cd_cnta_intg      varchar(40) not null,
    dh_cad            timestamp   not null,
    cd_usu_atu        varchar(25) not null,
    dh_atu            timestamp   not null,
    id_sit            varchar(1)  not null
);

create index idx_conta_integradora_cfop_id_cnta_intg on tbl_conta_integradora_cfop (cd_cnta_intg);

/*********************************************************************************
                                  Solicitação CFOP
**********************************************************************************/

create table tbl_solicitacao_cfop
(
    id_sol_cfop       bigserial                   not null
        constraint pk_solicitacao_cfop
            primary key,
    id_cnta_intg_cfop bigint
        constraint fk_solicitacao_cfop_conta_integradora_cfop
            references tbl_conta_integradora_cfop not null,
    ds_trn            varchar(255)                not null,
    cd_moeda          varchar(3)                  not null,
    vr_sol            numeric(18, 2)              not null,
    num_ref           varchar(50),
    prt_vrs_revisao   smallint                    not null,
    prt_vrs_menor     smallint                    not null,
    prt_vrs_maior     smallint                    not null,
    req_id            varchar(36)                 not null,
    req_time          timestamp                   not null,
    trace_id          varchar(20)                 not null,
    cd_usu_atu        varchar(25)                 not null,
    dh_atu            timestamp                   not null,
    id_sit            varchar(1)                  not null
);

create index idx_solicitacao_cfop_req_id on tbl_solicitacao_cfop (req_id);
create index idx_solicitacao_cfop_trace_id on tbl_solicitacao_cfop (trace_id);
