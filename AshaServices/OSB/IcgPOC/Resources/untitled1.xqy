xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/top/OsbInstances";
(:: import schema at "OsbInstances_table.xsd" ::)

declare variable $req as xs:integer external;
declare variable $uuid as xs:integer external;
declare variable $time as xs:gMonthDay external;
declare variable $sta as xs:integer external;
declare variable $body as xs:integer external;

declare function local:func($req as xs:integer, 
                            $uuid as xs:integer, 
                            $time as xs:gMonthDay, 
                            $sta as xs:integer, 
                            $body as xs:integer) 
                            as element() (:: schema-element(ns1:OsbInstancesCollection) ::) {
    <ns1:OsbInstancesCollection>
        <ns1:OsbInstances>
            <ns1:messageid>{fn:data($req)}</ns1:messageid>
            <ns1:uuid>{fn:data($uuid)}</ns1:uuid>
            <ns1:timestamp>{fn:data($time)}</ns1:timestamp>
            <ns1:body>{fn:data($sta)}</ns1:body>
            <ns1:status>{fn:data($body)}</ns1:status>
        </ns1:OsbInstances>
    </ns1:OsbInstancesCollection>
};

local:func($req, $uuid, $time, $sta, $body)
