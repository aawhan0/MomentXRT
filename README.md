# MomentX RT

Real-time moment detection for live streams.

MomentX RT is a lightweight server-side system built on Ant Media Server that detects important moments **during** a live broadcast and instantly generates timeline markers and short highlight clips.

Built for the Ant Media AI Hackathon 2026.

---

## What It Does

- Monitors live streams in real time  
- Detects moments using rule-based triggers  
- Generates timeline markers instantly  
- Creates short highlight clips during the broadcast  
- Supports manual producer trigger (fallback)  
- Stores confidence score and detection reason  
- Provides post-stream ranked summary of moments  

All processing happens while the stream is ongoing.

---

## Architecture

## Project Structure

```text
/MomentX_RT/
  ├─ server/     # Core service & Ant Media integration
  ├─ triggers/   # Automatic + Manual detection logic
  ├─ clipper/    # Rolling buffer & Clip generation
```
---


MomentX RT operates as a modular backend service using stream IDs from Ant Media Server.

---

## Technology Stack

- Ant Media Server (WebRTC streaming)  
- Python-based backend service  
- Real-time audio intensity detection  
- FFmpeg for clip extraction  
- JSON-based configuration and lightweight storage  

---

## Design Principles

- Low latency first  
- Reliability over complexity  
- Explainable detection (confidence + reason tagging)  
- Modular and extensible structure  

---

## Hackathon

Ant Media AI Hackathon 2026  
**Team: error200**  
Members:
- Archisha Nigam  
- Kshitij Pagar  
