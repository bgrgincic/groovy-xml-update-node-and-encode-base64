import groovy.xml.XmlUtil

// Import file
def xmlFromFile = new File('D:\\Path\\file.xml')
def envelopeNode = new XmlSlurper().parseText(xmlFromFile.getText('UTF-8'))

// Generate random node value
def now = new Date()
time = now.format("yyyyMMdd-HH:mm:ss.SSS", TimeZone.getTimeZone('UTC'))
random = Math.abs(new Random().nextInt() % 600) + 1

// Update node with new value
envelopeNode.ID='R1D' + time + random
 
// Recreate XML file as string 
XmlUtil xmlUtil = new XmlUtil()
//log.info xmlUtil.serialize(envelopeNode) //Just to print it out
String changedXML = xmlUtil.serialize(envelopeNode)

//  Encode in base64
String encodedFile = changedXML.bytes.encodeBase64().toString()

// Write as a variable (for SOAP UI), later usage in requests: ${#TestCase#stateCode}
testRunner.testCase.setPropertyValue('stateCode', encodedFile)
