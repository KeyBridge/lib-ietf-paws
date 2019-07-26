Internet Engineering Task Force (IETF)   
Request for Comments: 7545            
Category: Standards Track             
May 2015   
                                                                
# Protocol to Access White-Space (PAWS) Databases


Portions of the radio spectrum that are allocated to licensees are
available for non-interfering use.  This available spectrum is called
"white space".  Allowing secondary users access to available spectrum
"unlocks" existing spectrum to maximize its utilization and to
provide opportunities for innovation, resulting in greater overall
spectrum utilization.

**Protocol Overview**

    A Master Device uses PAWS to obtain a schedule of available spectrum
    at its location.

A typical sequence of PAWS operations is outlined as follows.
      
1.   The Master Device obtains (statically or dynamically) the URI
for a Database appropriate for its location, to which to send
subsequent PAWS messages.
2.   The Master Device establishes an HTTPS session with the
Database.
3.   The Master Device optionally sends an initialization message to
the Database to exchange capabilities.
4.   If the Database receives an initialization message, it responds
with an initialization-response message in the body of the HTTP
response.
5.   The Database may require the Master Device to be registered
before providing service.
6.   The Master Device sends an available-spectrum request message to
the Database.  The message may be on behalf of a Slave Device
that made a request to the Master Device.
7.   If the Master Device is making a request on behalf of a Slave
Device, the Master Device may verify with the Database that the
Slave Device is permitted to operate.
8.   The Database responds with an available-spectrum response
message in the body of the HTTP response.
9.   The Master Device may send a spectrum-usage notification message
to the Database. 
10.  If the Database receives a spectrum-usage notification message,
it responds by sending the Master Device a spectrum-usage
acknowledgement message.



**References**
   
  * [rfc7545](https://www.rfc-editor.org/rfc/rfc7545.html)
