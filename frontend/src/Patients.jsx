import { useEffect, useState } from "react";

function Patients() {
  const [patients, setPatients] = useState([]);
  const [search, setSearch] = useState("");
  const [selectedPatient, setSelectedPatient] = useState(null);
  const [clinicalReport, setClinicalReport] = useState([]);
  const [labResults, setLabResults] = useState([]);
  const [loading, setLoading] = useState(true);
  const [detailsLoading, setDetailsLoading] = useState(false);

  useEffect(() => {
    fetch("http://localhost:8080/api/patients")
      .then((response) => {
        if (!response.ok) {
          throw new Error("Failed to load patients");
        }

        return response.json();
      })
      .then((data) => {
        setPatients(data);
        setLoading(false);
      })
      .catch((error) => {
        console.error("Patient loading error:", error);
        setLoading(false);
      });
  }, []);

  const openPatientDetails = (patient) => {
    setSelectedPatient(patient);
    setClinicalReport([]);
    setLabResults([]);
    setDetailsLoading(true);

    Promise.all([
      fetch(
        `http://localhost:8080/api/patients/${patient.patientId}/clinical-report`
      ).then((response) => response.json()),

      fetch(
        `http://localhost:8080/api/patients/${patient.patientId}/lab-results`
      ).then((response) => response.json()),
    ])
      .then(([clinicalData, labData]) => {
        setClinicalReport(clinicalData);
        setLabResults(labData);
        setDetailsLoading(false);
      })
      .catch((error) => {
        console.error("Patient details error:", error);
        setDetailsLoading(false);
      });
  };

  const closeDetails = () => {
    setSelectedPatient(null);
    setClinicalReport([]);
    setLabResults([]);
  };

  const filteredPatients = patients.filter((patient) =>
    `${patient.firstName} ${patient.lastName}`
      .toLowerCase()
      .includes(search.toLowerCase())
  );

  if (selectedPatient) {
    return (
      <div className="page">

        <div className="page-header">
          <div>
            <h1>Patient Details</h1>
            <p>Clinical information and laboratory results</p>
          </div>

          <button
            className="primary-button"
            onClick={closeDetails}
          >
            Back to Patients
          </button>
        </div>

        <div className="report-header-card">

          <div>
            <span>Patient</span>
            <strong>
              {selectedPatient.firstName} {selectedPatient.lastName}
            </strong>
          </div>

          <div>
            <span>Patient ID</span>
            <strong>{selectedPatient.patientId}</strong>
          </div>

          <div>
            <span>Gender</span>
            <strong>{selectedPatient.gender}</strong>
          </div>

          <div>
            <span>Blood Group</span>
            <strong>{selectedPatient.bloodGroup}</strong>
          </div>

        </div>

        {detailsLoading ? (
          <p>Loading patient clinical information...</p>
        ) : (
          <>
            <div className="panel">
              <div className="panel-header">
                <div>
                  <h2>Clinical History</h2>
                  <p>Diagnosis and medication information</p>
                </div>
              </div>

              {clinicalReport.length === 0 ? (
                <p>No clinical history available.</p>
              ) : (
                <div className="table-card">
                  <table>
                    <thead>
                      <tr>
                        <th>Encounter</th>
                        <th>Date & Time</th>
                        <th>Doctor</th>
                        <th>Diagnosis</th>
                        <th>Type</th>
                        <th>Medication</th>
                        <th>Dosage</th>
                        <th>Frequency</th>
                      </tr>
                    </thead>

                    <tbody>
                      {clinicalReport.map((item, index) => (
                        <tr key={index}>
                          <td>{item.encounterId}</td>
                          <td>{item.encounterTime}</td>
                          <td>{item.doctorName}</td>
                          <td>
                            <strong>{item.diagnosisName}</strong>
                          </td>
                          <td>{item.diagnosisType}</td>
                          <td>{item.medicationName}</td>
                          <td>{item.dosage}</td>
                          <td>{item.frequency}</td>
                        </tr>
                      ))}
                    </tbody>
                  </table>
                </div>
              )}
            </div>

            <div className="panel" style={{ marginTop: "20px" }}>
              <div className="panel-header">
                <div>
                  <h2>Laboratory Results</h2>
                  <p>Patient laboratory test results</p>
                </div>
              </div>

              {labResults.length === 0 ? (
                <p>No laboratory results available.</p>
              ) : (
                <div className="table-card">
                  <table>
                    <thead>
                      <tr>
                        <th>Test</th>
                        <th>Result</th>
                        <th>Unit</th>
                        <th>Reference Range</th>
                        <th>Status</th>
                      </tr>
                    </thead>

                    <tbody>
                      {labResults.map((result) => (
                        <tr key={result.labResultId}>
                          <td>
                            <strong>{result.testName}</strong>
                          </td>
                          <td>{result.resultValue}</td>
                          <td>{result.unit}</td>
                          <td>{result.referenceRange}</td>
                          <td>
                            <span className="status-badge">
                              {result.status}
                            </span>
                          </td>
                        </tr>
                      ))}
                    </tbody>
                  </table>
                </div>
              )}
            </div>
          </>
        )}
      </div>
    );
  }

  return (
    <div className="page">

      <div className="page-header">
        <div>
          <h1>Patients</h1>
          <p>Manage and view patient information</p>
        </div>

        <input
          type="text"
          placeholder="Search patient..."
          value={search}
          onChange={(e) => setSearch(e.target.value)}
          className="search-input"
        />
      </div>

      {loading ? (
        <p>Loading patients...</p>
      ) : (
        <div className="table-card">
          <table>
            <thead>
              <tr>
                <th>ID</th>
                <th>Patient Name</th>
                <th>Date of Birth</th>
                <th>Gender</th>
                <th>Phone</th>
                <th>Email</th>
                <th>Blood Group</th>
                <th>Status</th>
              </tr>
            </thead>

            <tbody>
              {filteredPatients.map((patient) => (
                <tr
                  key={patient.patientId}
                  onClick={() => openPatientDetails(patient)}
                  style={{ cursor: "pointer" }}
                >
                  <td>{patient.patientId}</td>

                  <td>
                    <strong>
                      {patient.firstName} {patient.lastName}
                    </strong>
                  </td>

                  <td>{patient.dateOfBirth}</td>
                  <td>{patient.gender}</td>
                  <td>{patient.phone}</td>
                  <td>{patient.email}</td>
                  <td>{patient.bloodGroup}</td>

                  <td>
                    <span className="status-badge">
                      {patient.status}
                    </span>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>

          {filteredPatients.length === 0 && (
            <p className="empty-message">
              No patients found.
            </p>
          )}
        </div>
      )}

    </div>
  );
}

export default Patients;