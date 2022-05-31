package com.imooc.ecommerce.constant;

/**
 * 授权需要使用的常量信息
 *
 * @author tangdapeng
 * @description
 * @create 2022/05/14
 */
public class AuthorityConstant {
//    /** 私钥 */
//    public static final String PRIVATE_KEY ="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDR9ylnLFPdc7wMXKyt8Z" +
//            "wbe0YQY5Xb2V0SNMYy3klW1EDT3Z9APY+xq8SvfHHBRngDJ7u1RQZgm2y1gcPTjI6ZJkuxxlOKt7iKMG1XpxecBH9K+fsVm" +
//            "qI2yYAPz4LZ7IQj6TcISjtreR1M80xWkmYtUoYjVwRSN+wAD9qesGhreoV1dAhXHIejb+zOyydTSUVqTT8sXxzUf9eHk5SA" +
//            "E98G+iX5T+fxDHe//0f7GS9PW0TzYvVAjzfiZ0tlwD3W1uu8a4FkInaC00lMySVrLYg6VS+Te6Io+YyujApnz4sLCEmAGa7" +
//            "BOULN8EeaeLcXuukZGARPmEZQrYh9MCf9GGpbAgMBAAECggEBAJbuzJV9GQBdPPXJ/NvBjg13TUNkwwpaM3xt8XgJsMC5La" +
//            "zfaKYeWthGeMoJdCCSzpy+CEJjTxVNRRUpJiNBrBRNoiY2QkG6le7IpyFWXTNrTDIShzrdgPRPXz5+VjrfuZGzCTttWU+Fr" +
//            "dluIBcvgEHeS8zFtbaAuqbEvsxKVZXEG+fmXHJEWuN0NR843e4MlkS3PquYPiKOr3DnrELDYA70nUJ1LBXfSDTSv+2fGU8f" +
//            "7rsJp/ox8eJMdVaG9975lUZhqarUE0Fi2ErF86gZZuzwdjhRRrGMCOzapBZHA3Afby/ez4ZtgygDx/LR/UvCAekN3UTo3bA" +
//            "sDvqVeosHwxECgYEA/Q2wNdWXqpd+PCDY8hHQh3CjjlsjpNwtlvm/LB/cqU1jGxTkud9VeuA3NFb94souThJivPWGMZL6pu" +
//            "joltSzPxQuJ0LuL4OP6Wf4XOAJrkEXmL++caTtkvDqi3yqb3C1W6hix1R5HbLJRHhXlJwlpf5Dxu7xtfm8u2vL5tUkgfMCg" +
//            "YEA1GkI973WUsZW/LheWUcsrPLuzEJ/oOIsY2NV3aerQkEAQmqN0vO6Zl/LnZdis6O5YQrqm+B/ReAlRO0FC68VgbfV45Ap" +
//            "dTSD/eIeda+c0SnL5g+j9aJb5Vj++Klo3DQy0Wp1f5+sEZQ0lMFRbQFyh6uc9Uqp/q6EFSr459iOJ/kCgYEA4LRN+4/eLJJ" +
//            "3esckPdgJ1qrz4U4W2abWKNE+q9zjIcdhMMQd0tt5WTJOcwCoU8F62sLCwM44yj6cqqf5GS5ouKOf1Cc/wBtQKE+1oCslDY" +
//            "IphO8HGnz8pVWewBwEcA6iTHC6vgvJvNBsnotKIeGCIOMmpji5lADeUYMIPcKYt3cCgYB7d/6le2zsuqvvxP/a53t5oNDcX" +
//            "L8xdqcUsrZD3OW44m3TLXr5J4GvZnnGRiwyfbYRSbYsG6f/pxYi5Z+sNf7LY1/qIm9m2LrsxhaslAMUtjX2qhrFerXuL/gt" +
//            "9NOmRJVRk49vOxJbOYoC+ghp05COelwHXXpJN4pBf7QlmISxiQKBgB8XX8D4Q7Z2wsaTXaNL9PMc+YNveX+pWh+9JQPpgg5" +
//            "CbMWzxDOla6FeFT8OONrWGPApKXvsYzu9ainM1cGDBAUxa2Hi+rkqxkVVOSno7KpjhAR5Hax+PRR2YW3tg/+9nQqtrnXPvh" +
//            "oQD82A4ogoILdPAMwE0NIOQHSjeZoqrzO0";

    /** RSA 私钥, 除了授权中心以外, 不暴露给任何客户端 */
    public static final String PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCdX6IaUTV7xnzbkb4KEjBEn" +
            "YfaM4wIG4dXsE+tQTx0AaOoCVG0JrEzInDs1X00hSX+tjtFYFRDB6WQaYa5CiadQAUiA31/xTFILob3fmWjVmgFUlZiZ+zlOhcvP59C6Cao" +
            "dm8X5VzmVQSjVhql6JrKaQ7EM5EwaPNAGjGgG3fXogDA0Q8i9xX4XpP5Y0Ulf+nlePjxnWu2Zs5Dx98X6kRYpkaxiD/1v6kqKoUm27IerCP" +
            "/2jEUET3wwK7lF8rozsV/3/V3I16fUcqMjsDCPxmdJx5VSV1xcR2c/z1hqB69uG1KX/4K8KwrAern5kuRy441LETh0gVZOPED3bAmNh/3Ag" +
            "MBAAECggEAeWW7S2fXxRpxWINNwMEVIOkQUh8vZ1WtCtTEMddABhD5qPc/YqPyT4rC578sLWaEz77NbBAKEAcHDaaJuZX+AZHQI9PjbLu17" +
            "4OMs8B6MQzHg0tnvdODZ7RvDBCdD2S75Xou6pURG9D7mfAi8FbFiVyPeoR4/IoJEyrhmyt1QA3PsTUCByAdOGqnoftlGsKvmzdkW/6C+iBA" +
            "cjGkB+k498Nzzh80BlD0ToKDJe3Hf2cfXbjrZRkFIEAXzv7FNKniB8AXZraC/oRz8LI4y4u+ppCfG/cQUzdpdyizIPyfeZnbZrBzzem07Jq" +
            "ZdM1teyWRGJpxFpc/NJICIGdkitsrAQKBgQDI5wCWjET1G+1fALFKB/BKZrl4Rcq0QpXHiRq7wqakDMcV2JVzxCePR7hhGdGo6I8Hl82lyq" +
            "euVJEqSj7N3EmlghoyGxmyVBjjf/U1vPJ7HNrxEZP/3RNFX9kt7LkBOeRewk+7eP10k+uMdjqYaGc4bApvgSTKgUh6yu9//669dwKBgQDIi" +
            "I1YH78ir7rEtDeqInBC1+lL1rQ2ZLR6kkG2rnoNaoVWBJFhMXPUwaInAYZGIghbC9x5ETEoydbztDq+dANq4GL/h0sLAcEoH41/tZj4UjXr" +
            "BFlzgR2JQD3ngUp76HBr7OEZM8A13KLK6dmq4nFiHXoeAef3P3US7KWWufpRgQKBgA4K/6KGWYeclSIP5VSca3J37bz3e+zPvrJ85e5787Q" +
            "B5KSlSAUYj/9W7w9yg5I/90d7l/dfzArGhIMlP3CaVleIWZZpCInLkRXN6vD/rR9n6/Hv3Z2D8YXm5kJsip4IUutAv9wF4AmXEV73IK9olN" +
            "Z/ztbuewjgspVKvCUBVVCPAoGAVijFgf1x4/dY6bb8y66sSSv3YsxG9a/jE17PuSvR6P3BeCnSRM44lEr13oh7JprsuCGqgqHuWzYNkvv22" +
            "WmDJmJuZpe8mGgSk6iq5RojNV4tKGLgBA6PoC7n6hXU6NP/qX9HX8qqZPn9P+2lxhQw3xUHKOlsvwCWzqC6rL4PXQECgYEAsU6N2XVkjPD8" +
            "TAQF2RByje8ZVL4u/JK6BPJm4PRT2o1EvcHP4npQ6x4vmx/+qY5Ri2z13NZiXW+TbJ63bZN+cimWiH9ermSQef89JmQlFupJiugRqAjKrvo" +
            "+/MEntOvGmpoX+Cx1MjhKP/IkRDGYlUmrtKQOrnx4uGS+AxwABkA=";

    /** 默认的 Token 超时时间，一天 */
    public static final Integer DEFAULT_EXPIRE_DAY = 1;

}
