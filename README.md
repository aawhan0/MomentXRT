# MomentX RT

Real-time moment detection for live streams.

MomentX RT detects important moments **during** a live stream and instantly creates timeline markers and short clips while the broadcast is still ongoing.  
It is designed as a lightweight, server-side extension for Ant Media Server.

---

## What It Does

- Monitors live streams in real time  
- Detects important moments as they happen  
- Creates timeline markers and short clips instantly  
- Supports manual producer triggers as a fallback  

---

## Project Structure

```text
/momentx_rt/
  ├─ server/     # Core service & Ant Media integration
  ├─ triggers/   # Moment detection logic
  ├─ clipper/    # Clip generation

---

## Hackathon

- Ant Media AI Hackathon 2026
- Team: error200
- Project: MOmentX RT