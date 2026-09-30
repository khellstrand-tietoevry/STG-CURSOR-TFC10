# Data Objects — TFC10

## R115L050-Initial-Check
Source: `legacy/STG/TFC10/copybooks/R115L050.copy`

| Field | Type | Note |
|---|---|---|
| R115L050-Function | X | STG function code including position 3 R read flag |
| R115L050-Medium | X | Required with Function; spaces trigger C102 abend |
| R115L050-Operation-Type | X | Used with status in F115ISR0 VALID-ST-OP check |
| R115L050-PA-Seq-No | 9 | Zero skips amount reads |
| R115L050-Last-Update-TX | X | Compared in E000 when enabled |

Used by: FTFCK100, F115L050, ftfc-l050 InitialCheckService

## R7919999-Error-Area
Source: `legacy/STG/TFC10/copybooks/R7919999.copy`

| Field | Type | Note |
|---|---|---|
| R7919999-Return-Error | 88 | Soft error; Z700-Error-AE sets and Goback |
| R7919999-Return-Abend | 88 | Hard abend; Z900-Abend sets and Goback |
| R7919999-Paragraph | X | Diagnostic paragraph id e.g. C102, F102, G001 |

Used by: FTFCH100, FTFCK100, FTFCL100, F115L050, F115L020, F115L090
