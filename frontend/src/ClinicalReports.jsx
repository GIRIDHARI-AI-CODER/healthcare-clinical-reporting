import { useState } from "react";

function ClinicalReports() {
  const [patientId, setPatientId] = useState("");
  const [report, setReport] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const searchReport = () => {
    if (!patientId || Number(patientId) <= 0) {
      setError("Please enter a valid patient ID.");
      setReport([]);
      return;
    }

    setLoading(true);
    setError("");
    setReport([]);

    fetch(`http://localhost:8080/api/patients/${patientId}/clinical-report`)
      .then((response) => {
        if (!response.ok) {
          throw new Error("Clinical report not found.");
        }

        return response.json();
      })
      .then((data) => {
        setReport(data);
        setLoading(false);

        if (data.length === 0) {
          setError("No clinical report found for this patient.");
        }
      })
      .catch((err) => {
        console.error("Clinical report error:", err);
        setError("Unable to load clinical report.");
        setLoading(false);
      });
  };

  return (
    <div className="page">
      <div className="page-header">
        <div>
          <h1>Clinical Reports</h1>
          <p>Patient clinical history and treatment information</p>
        </div>

        <div className="report-search">
          <input
            type="number"
            placeholder="Enter Patient ID"
            value={patientId}
            onChange={(e) => setPatientId(e.target.value)}
            className="search-input"
          />

          <button
            className="primary-button"
            onClick={searchReport}
          >
            Generate Report
          </button>
        </div>
      </div>

      {loading && <p>Loading clinical report...</p>}

      {error && <p className="error-message">{error}</p>}

      {report.length > 0 && (
        <>
          <div className="report-header-card">
            <div>
              <span>Patient</span>
              <strong>{report[0].patientName}</strong>
            </div>

            <div>
              <span>Patient ID</span>
              <strong>{report[0].patientId}</strong>
            </div>

            <div>
              <span>Gender</span>
              <strong>{report[0].gender}</strong>
            </div>

            <div>
              <span>Blood Group</span>
              <strong>{report[0].bloodGroup}</strong>
            </div>
          </div>

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
                {report.map((item, index) => (
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
        </>
      )}
    </div>
  );
}

export default ClinicalReports;